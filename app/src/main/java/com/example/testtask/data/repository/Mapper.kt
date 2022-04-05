package com.example.testtask.data.repository

import com.example.testtask.data.storage.model.AnswersData
import com.example.testtask.data.storage.model.BriefCaseData
import com.example.testtask.data.storage.model.QuestionnairesData
import com.example.testtask.data.storage.model.QuestionsData
import com.example.testtask.domain.model.Answers
import com.example.testtask.domain.model.BriefCase
import com.example.testtask.domain.model.Questionnaires
import com.example.testtask.domain.model.Questions

class Mapper {
    companion object {

        fun briefcaseMapDataToDomain(briefCaseData: BriefCaseData): BriefCase {
            return BriefCase(
                briefCaseId = briefCaseData.briefCaseId,
                dateOfCreation = briefCaseData.dateOfCreation,
                inspector = briefCaseData.inspector,
                port = briefCaseData.port,
                inspectorName = briefCaseData.inspectorName,
                inspectorType = briefCaseData.inspectorType,
                vessel = briefCaseData.vessel,
                category = briefCaseData.category,
            )
        }

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

        fun questionnairesЬapDataToDomain(questionnairesData: QuestionnairesData): Questionnaires {
            return Questionnaires(
                qid = questionnairesData.qid,
                title = questionnairesData.title
            )
        }

        fun questionsMapDataToDomain(questionsData: QuestionsData): Questions {
            return Questions(
                idQuestion = questionsData.idQuestion,
                questionID = questionsData.questionID,
                comment = questionsData.comment,
                dateOfInspection = questionsData.dateOfInspection,
                answer = questionsData.answer,
                questionCode = questionsData.questionCode,
                question = questionsData.question,
                commentForQuestion = questionsData.commentForQuestion,
                categoryID = questionsData.categoryID,
                origin = questionsData.origin,
                categoryNewID = questionsData.categoryNewID,
                isAnswered = questionsData.isAnswered,
                images = questionsData.images,
                briefCaseId = questionsData.briefCaseId
            )
        }

        fun questionsMapDomainToData(questions: Questions): QuestionsData {
            return QuestionsData(
                idQuestion = questions.idQuestion,
                questionID = questions.questionID,
                comment = questions.comment,
                dateOfInspection = questions.dateOfInspection,
                answer = questions.answer,
                questionCode = questions.questionCode,
                question = questions.question,
                commentForQuestion = questions.commentForQuestion,
                categoryID = questions.categoryID,
                origin = questions.origin,
                categoryNewID = questions.categoryNewID,
                isAnswered = questions.isAnswered,
                images = questions.images,
                briefCaseId = questions.briefCaseId
            )
        }

        fun answersMapDataToDomain (answersData: AnswersData): Answers {
            return Answers(
                answerId = answersData.answerId,
                answerValue = answersData.answerValue
            )
        }
    }




}