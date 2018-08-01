package com.sourabhv.dagger2sample.di

import javax.inject.Named
import javax.inject.Scope

@MustBeDocumented
@Scope
@Retention
annotation class ActivityScoped

@MustBeDocumented
@Scope
@Retention
@Target(AnnotationTarget.CLASS,
        AnnotationTarget.FILE,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentScoped

@Named("make_planet_card_id")
annotation class CartID