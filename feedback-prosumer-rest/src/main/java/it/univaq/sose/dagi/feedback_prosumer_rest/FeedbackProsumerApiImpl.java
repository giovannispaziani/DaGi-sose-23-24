package it.univaq.sose.dagi.feedback_prosumer_rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import it.univaq.sose.dagi.feedback_prosumer_rest.client.CustomerRESTClient;
import it.univaq.sose.dagi.feedback_prosumer_rest.client.FeedbackSOAPClient;
import it.univaq.sose.dagi.feedback_prosumer_rest.model.EventFeedbackReport;
import it.univaq.sose.dagi.feedback_prosumer_rest.model.Feedback;
import it.univaq.sose.dagi.wsdltypes.ServiceException_Exception;

@Service
public class FeedbackProsumerApiImpl implements FeedbackProsumerApi {

	private FeedbackSOAPClient feedbackClient;
	private CustomerRESTClient customerClient;
	
	
	public FeedbackProsumerApiImpl(FeedbackSOAPClient feedbackClient, CustomerRESTClient customerClient) {
		super();
		this.feedbackClient = feedbackClient;
		this.customerClient = customerClient;
	}



	@Override
	public ResponseEntity<EventFeedbackReport> getEventFeedbackReport(long eventId, String keywords) throws ServiceException_Exception {
		try {
			//Fetch the list of feedbacks
			List<Feedback> feedbacks = feedbackClient.requestEventFeedbacks(eventId);
			
			//Fetch the ages of customers that left the feedbacks
			Set<Long> userIdsSet = new HashSet<>();
			feedbacks.forEach(feedback -> {userIdsSet.add(feedback.getUserId());});
			Long[] userIds = new Long[userIdsSet.size()];
			JsonNode userInfos = customerClient.fetchUsersInfo(userIdsSet.toArray(userIds));

			EventFeedbackReport report = new EventFeedbackReport();
			float averageRating = 0.0f;
			float averageAge = 0.0f;
			Map<String, Integer> keywordCounts = null;
			
			//Compute the average age of feedback users
			if(userInfos.isArray()) {
				for(JsonNode user : userInfos) {
					averageAge += user.findValue(CustomerRESTClient.FIELD_AGE).asInt();
				}
				
				averageAge /= userIds.length;
			}
			
			//If keywords are provided, initialize the map
			if(keywords != null && !keywords.isBlank()) {
				keywordCounts = new HashMap<>();
				for(String keyword : keywords.split("[,;]")) {
					keywordCounts.put(keyword, 0);
				}
			}
			
			for(Feedback feedback : feedbacks) {
				//Average feedback rating
				averageRating += feedback.getRating();
				//Count keywords if any was provided in the request
				if(keywordCounts != null) {
					for(String keyword : keywordCounts.keySet()) {
						Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
				        Matcher matcher = pattern.matcher(feedback.getBody());
				        int count = 0;
				        while (matcher.find()) {
				            System.out.println(matcher.group());  // Output: Ciao, cIAo
				            count++;
				        }
						int previousKeywordCount = keywordCounts.get(keyword);
						keywordCounts.put(keyword, previousKeywordCount + count);
					}
				}
			}
			averageRating /= feedbacks.size();
			report.setAverageCustomerAge(averageAge);
			report.setAverageRating(averageRating);
			report.setEventFeedbacks(feedbacks);
			report.setKeywordsCount(keywordCounts);
			
			return new ResponseEntity<EventFeedbackReport>(report, HttpStatus.OK);
			
		} catch (ServiceException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
