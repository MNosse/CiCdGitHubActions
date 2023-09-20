package br.com.udesc.eso.tcc.studytalk.infrastructure.report.gateway.converter

import br.com.udesc.eso.tcc.studytalk.core.interfaces.Postable
import br.com.udesc.eso.tcc.studytalk.entity.answer.model.Answer
import br.com.udesc.eso.tcc.studytalk.entity.question.model.Question
import br.com.udesc.eso.tcc.studytalk.entity.report.model.Report
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.answer.AnswerSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.question.QuestionSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.report.ReportSchema

fun convert(reportSchema: ReportSchema): Report {
    return Report(
        id = reportSchema.id,
        postable = convert(reportSchema.postable)!!,
        description = reportSchema.description
    )
}

private fun convert(postable: Postable): Postable? {
    when (postable) {
        is AnswerSchema -> {
            return Answer(
                id = postable.id,
                description = postable.description,
                likeCount = postable.likeCount
            )
        }

        is QuestionSchema -> {
            return Question(
                id = postable.id,
                title = postable.title,
                description = postable.description,
                subjects = postable.subjects
            )
        }
    }
    return null
}