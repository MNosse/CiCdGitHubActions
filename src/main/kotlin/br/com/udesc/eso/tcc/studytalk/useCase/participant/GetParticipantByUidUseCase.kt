package br.com.udesc.eso.tcc.studytalk.useCase.participant

import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.participant.gateway.ParticipantGateway
import br.com.udesc.eso.tcc.studytalk.entity.participant.model.Participant
import org.springframework.stereotype.Service

@Service
class GetParticipantByUidUseCase(private val participantGateway: ParticipantGateway) {
    @Throws(ParticipantNotFoundException::class)
    fun execute(input: Input): Output {
        participantGateway.getByUid(input.uid)?.let {
            return Output(it)
        } ?: throw ParticipantNotFoundException()
    }

    data class Input(val uid: String)
    data class Output(val participant: Participant)
}