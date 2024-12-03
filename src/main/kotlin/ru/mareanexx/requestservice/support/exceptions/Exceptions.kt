package ru.mareanexx.requestservice.support.exceptions

// Неправильный или некорректный id был указан в параметрах
class WrongIDArgument(mes: String) : Exception(mes)