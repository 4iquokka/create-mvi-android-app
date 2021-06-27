package com.shinyj.template.mvi.cache.model

import com.shinyj.template.mvi.domain.model.SomeModel
import com.shinyj.template.mvi.domain.util.DomainMapper

class SomeEntityMapper : DomainMapper<SomeEntity, SomeModel> {

    override fun mapToDomainModel(model: SomeEntity): SomeModel {
        TODO("Not yet implemented")
    }

    override fun mapFromDomainModel(domainModel: SomeModel): SomeEntity {
        TODO("Not yet implemented")
    }
}