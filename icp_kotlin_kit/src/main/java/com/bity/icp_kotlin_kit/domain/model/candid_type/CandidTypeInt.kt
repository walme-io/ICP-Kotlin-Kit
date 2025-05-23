package com.bity.icp_kotlin_kit.domain.model.candid_type

import guru.zoroark.tegral.niwen.parser.ParserNodeDeclaration
import guru.zoroark.tegral.niwen.parser.reflective

internal class CandidTypeInt(
    override val typeId: String? = null,
    override val variableName: String = "intValue",
    override val optionalType: OptionalType = OptionalType.None,
    override val isTypeAlias: Boolean = false
) : CandidType() {

    override fun getKotlinType(variableName: String?): String = "BigInteger"

    companion object : ParserNodeDeclaration<CandidTypeInt> by reflective()
}