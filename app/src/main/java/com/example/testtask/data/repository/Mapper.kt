package com.example.testtask.data.repository

import com.example.testtask.data.storage.model.*
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.Questions

class Mapper {
    companion object {

        fun briefcaseMapDomainToData(briefCase: BriefCase): BriefCaseData {
            return BriefCaseData(
                briefCaseId = briefCase.briefCaseId,
                dateOfCreation = briefCase.dateOfCreation,
                inspector = briefCase.inspector,
                port = briefCase.port,
                inspectorName = briefCase.inspectorName,
                inspectorType = briefCase.inspectorType,
                vessel = briefCase.vessel,
                category = briefCase.category,
            )
        }

        fun questionnairesMapDataToDomain(questionnairesData: QuestionnairesData): Questionnaires {
            return Questionnaires(
                qid = questionnairesData.qid,
                title = questionnairesData.title
            )
        }

        fun questionsMapDataToDomain(questionsData: QuestionsData): Questions {
            return Questions(
                //     idQuestion = questionsData.idQuestion,
                questionid = questionsData.questionid,
                comment = questionsData.comment,
                dateOfInspection = questionsData.dateOfInspection,
                answer = questionsData.answer,
                questioncode = questionsData.questioncode,
                question = questionsData.question,
                commentForQuestion = questionsData.commentForQuestion,
                categoryid = questionsData.categoryid,
                origin = questionsData.origin,
                categorynewid = questionsData.categorynewid,
                isAnswered = questionsData.isAnswered,
                images = questionsData.images,
                briefCaseId = questionsData.briefCaseId
            )
        }

        fun questionsMapDomainToData(questions: Questions): QuestionsData {
            return QuestionsData(
                //       idQuestion = questions.idQuestion,
                questionid = questions.questionid,
                comment = questions.comment,
                dateOfInspection = questions.dateOfInspection,
                answer = questions.answer,
                questioncode = questions.questioncode,
                question = questions.question,
                commentForQuestion = questions.commentForQuestion,
                categoryid = questions.categoryid,
                origin = questions.origin,
                categorynewid = questions.categorynewid,
                isAnswered = questions.isAnswered,
                images = questions.images,
                briefCaseId = questions.briefCaseId
            )
        }

        fun answersMapDataToDomain(answersData: AnswersData): Answers {
            return Answers(
                answerId = answersData.answerId,
                answer = answersData.answer,
                answerDate = answersData.answerData
            )
        }
        fun answersMapDomainToData(answers: Answers): AnswersData {
            return AnswersData (
                answerId = answers.answerId,
                answer = answers.answer,
                answerData = answers.answerDate
            )
        }


    }


}

fun BriefCaseData.briefcaseMapDataToDomain(): BriefCase {
    return BriefCase(
        briefCaseId = briefCaseId,
        dateOfCreation = dateOfCreation,
        inspector = inspector,
        port = port,
        inspectorName = inspectorName,
        inspectorType = inspectorType,
        vessel = vessel,
        category = category,
        )
}