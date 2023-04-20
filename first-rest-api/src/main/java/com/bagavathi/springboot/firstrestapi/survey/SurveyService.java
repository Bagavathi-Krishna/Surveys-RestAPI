package com.bagavathi.springboot.firstrestapi.survey;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {
	public static List<Survey> surveys=new ArrayList<>();
	static {
		Question question1 = new Question("Question1",
		        "Most Popular Cloud Platform Today", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2",
		        "Fastest Growing Cloud Platform", Arrays.asList(
		                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3",
		        "Most Popular DevOps Tool", Arrays.asList(
		                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
		        question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey",
		        "Description of the Survey", questions);

		surveys.add(survey);
  
	}
	public List<Survey> retriveAllSurveys() {
		
		return surveys;
	}
	public Survey retrivetheSurvey(String surveyId) {
		
		for(Survey s:surveys)
		{
			if(s.getId().equalsIgnoreCase(surveyId))
			{
				return s;
			}
		}
		
		return null;
	}
	public List<Question> retrivetheSurveyQuestions(String surveyId) {
		for(Survey s:surveys)
		{
			if(s.getId().equalsIgnoreCase(surveyId))
			{
				return s.getQuestions();
			}
		}
		
		return null;
	}
	public Question retriveSpecSurveyQuest(String surveyId,String qid) {
		for(Survey s:surveys)
		{
			if(s.getId().equalsIgnoreCase(surveyId))
			{
				List<Question> questions=s.getQuestions();
				for(Question q:questions)
				{
					if(q.getId().equalsIgnoreCase(qid))
					{
						return q;
					}
				}
			}
		}
		return null;
	}
	public String addSurveyQuestion(String surveyId, Question question) {
		List<Question> qlist=retrivetheSurveyQuestions(surveyId);
		SecureRandom secureRandom=new SecureRandom();
		String randomId=new BigInteger(32,secureRandom).toString();
		question.setId(randomId);
		qlist.add(question);
		return question.getId();
	}
	
	public Question deleteSurveyQuestion(String surveyId,String qid) {
		for(Survey s:surveys)
		{
			if(s.getId().equalsIgnoreCase(surveyId))
			{
				List<Question> questions=s.getQuestions();
				for(int i=0;i<questions.size();i++)
				{
					
					if(questions.get(i).getId().equalsIgnoreCase(qid))
					{
						questions.remove(i);
						System.out.println(questions.toString());
						break;
					}
				}
			}
		}
		
		return null;
	}
	public void updateSurveyQuestion(String surveyId, String questionId, Question question) {
		deleteSurveyQuestion(surveyId, questionId);
		addSurveyQuestion(surveyId, question);
		
		
	}
}
