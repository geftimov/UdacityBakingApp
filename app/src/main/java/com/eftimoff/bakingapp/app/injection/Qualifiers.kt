package com.eftimoff.bakingapp.app.injection

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForApplication

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForActivity

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForFragment