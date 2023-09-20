package br.com.udesc.eso.tcc.studytalk.useCase.participant

import br.com.udesc.eso.tcc.studytalk.entity.answer.exception.AnswerNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.participant.gateway.ParticipantGateway
import org.springframework.stereotype.Service

@Service
class ChangeAnAnswerLikeStatusUseCase(private val participantGateway: ParticipantGateway) {
    @Throws(ParticipantNotFoundException::class, AnswerNotFoundException::class)
    fun execute(input: Input) {
        participantGateway.getByUid(input.participantUid)?.let {
            participantGateway.changeAnAnswerLikeStatus(
                participantUid = input.participantUid,
                answerId = input.answerId
            )
        } ?: throw ParticipantNotFoundException()
    }

    data class Input(
        val participantUid: String,
        val answerId: Long
    )
}