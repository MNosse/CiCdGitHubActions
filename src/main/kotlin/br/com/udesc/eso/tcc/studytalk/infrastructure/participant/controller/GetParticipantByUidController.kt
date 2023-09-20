package br.com.udesc.eso.tcc.studytalk.infrastructure.participant.controller

import br.com.udesc.eso.tcc.studytalk.core.infrastructure.controller.BaseController
import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
import br.com.udesc.eso.tcc.studytalk.infrastructure.participant.controller.converter.convert
import br.com.udesc.eso.tcc.studytalk.infrastructure.participant.controller.response.Response
import br.com.udesc.eso.tcc.studytalk.useCase.participant.GetParticipantByUidUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/studytalk/api/participants")
class GetParticipantByUidController(private val getParticipantByUidUseCase: GetParticipantByUidUseCase) : BaseController() {
    @GetMapping("/{uid}")
    @ResponseStatus(HttpStatus.FOUND)
    @Throws(ParticipantNotFoundException::class)
    fun getParticipantByUid(@PathVariable uid: String): Response {
        return convert(getParticipantByUidUseCase.execute(GetParticipantByUidUseCase.Input(uid)).participant)
    }
}