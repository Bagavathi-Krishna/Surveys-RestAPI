package com.bagavathi.springboot.firstrestapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResources {
	@Autowired
	private SurveyService surveyService;
	@RequestMapping("/surveys")
	public List<Survey> retrieveAllSurveys()
	{
		return surveyService.retriveAllSurveys();
	}
	@RequestMapping("/surveys/{surveyId}")
	public Survey retrievetheSurvey(@PathVariable String surveyId)
	{
		Survey survey=surveyService.retrivetheSurvey(surveyId);
		if(survey==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else
			return survey;
	}
	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrievetheSurveyQuests(@PathVariable String surveyId)
	{
		List<Question> list=surveyService.retrivetheSurveyQuestions(surveyId);
		if(list==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else
			return list;
	}
	
	@RequestMapping("/surveys/{surveyId}/questions/{qid}")
	public Question retrieveSpecSurveyQuest(@PathVariable String surveyId,@PathVariable String qid)
	{
		Question question=surveyService.retriveSpecSurveyQuest(surveyId,qid);
		if(question==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		else
			return question;
	}
	@RequestMapping(value="/surveys/{surveyId}/questions",method=RequestMethod.POST)
	public ResponseEntity<Object> addSurveyQuestion(@PathVariable String surveyId,
			@RequestBody Question question)
	{
		String questionId=surveyService.addSurveyQuestion(surveyId,question);
		//surveys/{surveyId}/questions/{questionId}---->Path of newly created question
		
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{questionId}").buildAndExpand(questionId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}",method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId)
	{
		surveyService.deleteSurveyQuestion(surveyId,questionId);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/surveys/{surveyId}/questions/{questionId}",method=RequestMethod.PUT)
	public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId,
			@PathVariable String questionId,@RequestBody Question question)
	{
		surveyService.updateSurveyQuestion(surveyId,questionId,question);
		
		return ResponseEntity.noContent().build();
	}
}
