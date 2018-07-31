package com.sourabhv.dagger2sample.di

import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention
annotation class ActivityScope

@MustBeDocumented
@Scope
@Retention
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScope

@MustBeDocumented
@Scope
@Retention
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
annotation class SubFragmentScope
