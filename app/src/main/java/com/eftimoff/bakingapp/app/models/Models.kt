package com.eftimoff.bakingapp.app.models

data class Recipe(val id: Long, val name: String, val ingredients: List<Ingredient>, val steps: List<Step>, val servings: Long, val image: String)

data class Ingredient(val quantity: Double, val measure: String, val ingredient: String)

data class Step(val id: Long, val shortDescription: String, val description: String, val videoURL: String, val thumbnailURL: String)