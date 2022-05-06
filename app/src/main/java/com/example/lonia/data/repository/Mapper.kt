package com.example.lonia.data.repository

import com.example.lonia.data.storage.model.*
import com.example.lonia.domain.model.*

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
        answerDate = answerData,
        answerChoice = answerChoice,

        )
}

fun Answers.answersMapDomainToData(): AnswersData {
    return AnswersData(
        answerId = answerId,
        answer = answer,
        answerData = answerDate,
        answerChoice = answerChoice,

        )
}

fun LoginRequest.loginMapDomainToData(): LoginRequestData {
    return LoginRequestData(
        email = email,
        password = password
    )
}

fun Photos.photosMapDomainToData(): PhotosData {
    return PhotosData(
        photoId = photoId,
        questionId = questionId,
        photoUri = photoUri
    )
}

fun PhotosData.photosMapDataToDomain(): Photos {
    return Photos(
        photoId = photoId,
        questionId = questionId,
        photoUri = photoUri
    )
}

fun Notes.notesMapDomainToData(): NotesData {
    return NotesData(
        noteId = noteId,
        noteValue = noteValue,
        briefcaseId = briefcaseId,
        noteName = noteName
    )
}

fun NotesData.notesMapDataToDomain(): Notes {
    return Notes(
        noteId = noteId,
        noteValue = noteValue,
        briefcaseId = briefcaseId,
        noteName = noteName
    )
}