package com.zaidan.simplejetpackcompose.ui.main

enum class NewsCategory(val value: String) {
    BUSINESS("Business"),
    ENTERTAINMENT("Entertainment"),
    HEALTH("Health"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    TECHNOLOGY("Technology")
}

fun getAllNewsCategory(): List<NewsCategory> {
    return listOf(
        NewsCategory.BUSINESS,
        NewsCategory.ENTERTAINMENT,
        NewsCategory.HEALTH,
        NewsCategory.SCIENCE,
        NewsCategory.SPORTS,
        NewsCategory.TECHNOLOGY
    )
}

fun getNewsCategory(value: String): NewsCategory? {
    val map = NewsCategory.values().associateBy(NewsCategory::value)
    return map[value]
}