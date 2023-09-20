package br.com.udesc.eso.tcc.studytalk.infrastructure.participant.gateway

import br.com.udesc.eso.tcc.studytalk.core.enums.Privilege
import br.com.udesc.eso.tcc.studytalk.core.enums.Subject
import br.com.udesc.eso.tcc.studytalk.entity.participant.gateway.ParticipantGateway
import br.com.udesc.eso.tcc.studytalk.entity.participant.model.Participant
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.answer.AnswerRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.enrollmentRequest.EnrollmentRequestRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.institution.InstitutionRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.participant.ParticipantRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.repository.question.QuestionRepository
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.answer.AnswerSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.enrollmentRequest.EnrollmentRequestSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.participant.ParticipantSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.config.db.schema.question.QuestionSchema
import br.com.udesc.eso.tcc.studytalk.infrastructure.participant.gateway.converter.convert
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class ParticipantDatabaseGateway(
    private val answerRepository: AnswerRepository,
    private val enrollmentRequestRepository: EnrollmentRequestRepository,
    private val institutionRepository: InstitutionRepository,
    private val participantRepository: ParticipantRepository,
    private val questionRepository: QuestionRepository
) : ParticipantGateway {
    override fun answerAQuestion(participantUid: String, questionId: Long, description: String) {
        participantRepository.findByUid(participantUid)?.let { participant ->
            questionRepository.findById(questionId).getOrNull()?.let {
                answerRepository.save(
                    AnswerSchema(
                        description = description,
                        question = it,
                        participant = participant
                    )
                )
            }
        }
    }

    override fun changeAnAnswerLikeStatus(participantUid: String, answerId: Long) {
        participantRepository.findByUid(participantUid)?.let { participant ->
            answerRepository.findById(answerId).getOrNull()?.let {
                when (participant.likedAnswers.contains(it)) {
                    true -> {
                        it.likeCount--
                        answerRepository.save(it)
                        participant.likedAnswers.remove(it)
                    }

                    false -> {
                        it.likeCount++
                        answerRepository.save(it)
                        participant.likedAnswers.add(it)
                    }
                }
                participantRepository.save(participant)
            }
        }
    }

    override fun changeAQuestionFavoriteStatus(participantUid: String, questionId: Long) {
        participantRepository.findByUid(participantUid)?.let { participant ->
            questionRepository.findById(questionId).getOrNull()?.let {
                when (participant.favoriteQuestions.contains(it)) {
                    true -> participant.favoriteQuestions.remove(it)
                    false -> participant.favoriteQuestions.add(it)
                }
                participantRepository.save(participant)
            }
        }
    }

    override fun create(registrationCode: String, uid: String, name: String) {
        institutionRepository.findByRegistrationCode(registrationCode)?.let {
            val participant = participantRepository.save(ParticipantSchema(uid = uid, name = name))
            enrollmentRequestRepository.save(
                EnrollmentRequestSchema(
                    institution = it,
                    participant = participant
                )
            )
        }
    }

    override fun delete(uid: String) {
        participantRepository.findByUid(uid)?.let {
            participantRepository.deleteByUid(uid)
        }
    }

    override fun doAQuestion(
        participantUid: String,
        title: String,
        description: String,
        subjects: MutableList<Subject>
    ) {
        participantRepository.findByUid(participantUid)?.let {
            questionRepository.save(
                QuestionSchema(
                    title = title,
                    description = description,
                    subjects = subjects,
                    participant = it,
                    institution = it.institution!!
                )
            )
        }
    }

    override fun getAll(): MutableList<Participant> {
        return participantRepository.findAll().map {
            convert(it)
        }.toMutableList()
    }

    override fun getAllByInstitutionId(id: Long): MutableList<Participant> {
        return participantRepository.findAllByInstitutionId(id).map {
            convert(it)
        }.toMutableList()
    }

    override fun getByUid(uid: String): Participant? {
        return participantRepository.findByUid(uid)?.let {
            convert(it)
        }
    }

    override fun update(uid: String, name: String) {
        participantRepository.findByUid(uid)?.let {
            it.name = name
            participantRepository.save(it)
        }
    }

    override fun updatePrivilege(uid: String, privilege: Privilege) {
        participantRepository.findByUid(uid)?.let {
            it.privilege = privilege
            participantRepository.save(it)
        }
    }
}