package com.example.testtask.data.repository

import com.example.testtask.data.storage.model.*
import com.example.testtask.domain.model.*

class Mapper {

}

fun BriefCase.briefcaseMapDomainToData(): BriefCaseData {
    return BriefCaseData(
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

fun QuestionnairesData.questionnairesMapDataToDomain(): Questionnaires {
    return Questionnaires(
        qid = qid,
        title = title
    )
}

fun QuestionsData.questionsMapDataToDomain(): Questions {
    return Questions(
        questionid = questionid,
        comment = comment,
        dateOfInspection = dateOfInspection,
        answer = answer,
        questioncode = questioncode,
        question = question,
        commentForQuestion = commentForQuestion,
        categoryid = categoryid,
        origin = origin,
        categorynewid = categorynewid,
        isAnswered = isAnswered,
        images = images,
        briefCaseId = briefCaseId
    )
}

fun Questions.questionsMapDomainToData(): QuestionsData {
    return QuestionsData(
        questionid = questionid,
        comment = comment,
        dateOfInspection = dateOfInspection,
        answer = answer,
        questioncode = questioncode,
        question = question,
        commentForQuestion = commentForQuestion,
        categoryid = categoryid,
        origin = origin,
        categorynewid = categorynewid,
        isAnswered = isAnswered,
        images = images,
        briefCaseId = briefCaseId
    )
}

fun AnswersData.answersMapDataToDomain(): Answers {
    return Answers(
        answerId = answerId,
        answer = answer,
        answerDate = answerData
    )
}
fun Answers.answersMapDomainToData(): AnswersData {
    return AnswersData (
        answerId = answerId,
        answer = answer,
        answerData = answerDate
    )
}

fun LoginRequest.loginMapDomainToData(): LoginRequestData {
    return LoginRequestData(
        email = email,
        password = password
    )
}

fun LoginResponseData.loginResponseDataToDomain(): LoginResponse{
    return LoginResponse(
        token = token
    )
}