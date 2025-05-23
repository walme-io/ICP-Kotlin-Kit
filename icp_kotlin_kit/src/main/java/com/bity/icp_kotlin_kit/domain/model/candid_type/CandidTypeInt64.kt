package com.bity.icp_kotlin_kit.domain.model.candid_type

import guru.zoroark.tegral.niwen.parser.ParserNodeDeclaration
import guru.zoroark.tegral.niwen.parser.reflective

internal data class CandidTypeInt64(
    override val typeId: String? = null,
    override val variableName: String = "int64Value",
    override val optionalType: OptionalType = OptionalType.None,
): CandidType() {

    override fun getKotlinType(variableName: String?): String = "Long"

    companion object : ParserNodeDeclaration<CandidTypeInt64> by reflective()
}