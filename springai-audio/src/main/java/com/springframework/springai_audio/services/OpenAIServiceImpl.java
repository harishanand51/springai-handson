package com.springframework.springai_audio.services;



import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import com.springframework.springai_audio.model.Question;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {
	
	private final OpenAiAudioSpeechModel speechModel;

	public OpenAIServiceImpl(OpenAiAudioSpeechModel speechModel) {
		super();
		this.speechModel = speechModel;
	}

	@Override
	public byte[] getSpeech(Question question) {
		
		OpenAiAudioSpeechOptions speechOptions = OpenAiAudioSpeechOptions.builder()
				                                 .voice(OpenAiAudioApi.SpeechRequest.Voice.ALLOY)
				                                 .speed(1.0f)
				                                 .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
				                                 .model(OpenAiAudioApi.TtsModel.TTS_1.value)
				                                 .build();
		
		SpeechPrompt speechPrompt = new SpeechPrompt(question.question());
		
		SpeechResponse response = speechModel.call(speechPrompt);
		
		return response.getResult().getOutput();

	}
	

	

}
