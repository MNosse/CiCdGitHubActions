package br.com.udesc.eso.tcc.studytalk.useCase.report

import br.com.udesc.eso.tcc.studytalk.core.enums.Privilege
import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.participant.exception.ParticipantWithoutPrivilegeException
import br.com.udesc.eso.tcc.studytalk.entity.participant.gateway.ParticipantGateway
import br.com.udesc.eso.tcc.studytalk.entity.report.exception.ReportNotFoundException
import br.com.udesc.eso.tcc.studytalk.entity.report.gateway.ReportGateway
import org.springframework.stereotype.Service

@Service
class ApproveReportUseCase(
    private val participantGateway: ParticipantGateway,
    private val reportGateway: ReportGateway
) {
    @Throws(
        ReportNotFoundException::class,
        ParticipantNotFoundException::class,
        ParticipantWithoutPrivilegeException::class
    )
    fun execute(input: Input) {
        participantGateway.getByUid(input.approverUid)?.let {
            if (it.privilege == Privilege.TEACHER || it.privilege == Privilege.PRINCIPAL) {
                reportGateway.getById(input.id)?.let {
                    reportGateway.approve(input.id)
                } ?: throw ReportNotFoundException()
            } else throw ParticipantWithoutPrivilegeException()
        } ?: throw ParticipantNotFoundException()
    }

    data class Input(
        val approverUid: String,
        val id: Long
    )
}