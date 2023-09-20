package br.com.udesc.eso.tcc.studytalk.entity.answer.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class AnswerIsNotFromParticipantException : Exception(
    "A resposta não pertence ao participante"
)