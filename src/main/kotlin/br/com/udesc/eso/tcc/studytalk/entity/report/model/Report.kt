package br.com.udesc.eso.tcc.studytalk.entity.report.model

import br.com.udesc.eso.tcc.studytalk.core.interfaces.Postable
import br.com.udesc.eso.tcc.studytalk.entity.institution.model.Institution

data class Report(
    val id: Long = 0L,
    val postable: Postable,
    val description: String,
    val institution: Institution? = null
)