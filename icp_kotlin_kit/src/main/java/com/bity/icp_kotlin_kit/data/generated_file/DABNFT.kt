package com.bity.icp_kotlin_kit.data.generated_file

import com.bity.icp_kotlin_kit.data.datasource.api.model.ICPPrincipalApiModel
import com.bity.icp_kotlin_kit.data.model.ValueToEncode
import com.bity.icp_kotlin_kit.data.model.candid.CandidDecoder
import com.bity.icp_kotlin_kit.data.repository.ICPQuery
import com.bity.icp_kotlin_kit.domain.model.ICPPrincipal
import com.bity.icp_kotlin_kit.domain.model.request.PollingValues
import com.bity.icp_kotlin_kit.domain.model.ICPSigningPrincipal
import com.bity.icp_kotlin_kit.domain.model.enum.ICPRequestCertification

/**
 * File generated using ICP Kotlin Kit Plugin
 */
object DABNFT {
    sealed class detail_value {
        data object True : detail_value()
        data object False : detail_value()
        class I64(
            val long: Long
        ): detail_value()

        class U64(
            val uLong: ULong
        ): detail_value()

        class Vec(
            val values: Array<detail_value>
        ): detail_value()

        class Slice(
            val values: Array<UByte>
        ): detail_value()

        class Text(
            val string: String
        ): detail_value()

        class Float(
            val double: Double
        ): detail_value()

        class Principal(
            val iCPPrincipal: ICPPrincipalApiModel
        ): detail_value()

    }

    class add_nft_input(
        val name: String,
        val description: String,
        val thumbnail: String,
        val frontend: String?,
        val principal_id: ICPPrincipalApiModel,
        val details: Array<add_nft_inputDetails>
    ) {

        class add_nft_inputDetails(
            val string: String,
            val detail_value: detail_value
        )

    }

    class nft_canister(
        val name: String,
        val description: String,
        val thumbnail: String,
        val frontend: String?,
        val principal_id: ICPPrincipalApiModel,
        val submitter: ICPPrincipalApiModel,
        val last_updated_by: ICPPrincipalApiModel,
        val last_updated_at: ULong,
        val details: Array<nft_canisterDetails>
    ) {

        class nft_canisterDetails(
            val string: String,
            val detail_value: detail_value
        )

    }

    sealed class operation_error {
        data object NotAuthorized : operation_error()
        data object NonExistentItem : operation_error()
        data object BadParameters : operation_error()
        class Unknown(
            val string: String
        ): operation_error()

    }

    sealed class operation_response {
        class Ok(
            val string: String?
        ): operation_response()

        class Err(
            val operation_error: operation_error
        ): operation_response()

    }

    class DABNFTService(
        private val canister: ICPPrincipal
    ) {

        // DRS methods
        suspend fun name         (
            certification: ICPRequestCertification = ICPRequestCertification.Uncertified,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): String {

            val icpQuery = ICPQuery(
                methodName = "name",
                canister = canister
            )

            val result = icpQuery.invoke(
                values = null,
                sender = sender,
                pollingValues = pollingValues,
                certification = certification
            ).getOrThrow()

            return CandidDecoder.decodeNotNull(result.first())
        }

        suspend fun get                    (
            nft_id: ICPPrincipalApiModel,
            certification: ICPRequestCertification = ICPRequestCertification.Uncertified,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): nft_canister? {

            val icpQuery = ICPQuery(
                methodName = "get",
                canister = canister
            )

            val result = icpQuery.invoke(
                values = listOf(
                    ValueToEncode(nft_id)
                ),
                sender = sender,
                pollingValues = pollingValues,
                certification = certification
            ).getOrThrow()

            return CandidDecoder.decode(result.first())
        }

        suspend fun add                    (
            trusted_source: ICPPrincipalApiModel?,
            nft: add_nft_input,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): operation_response {

            val icpQuery = ICPQuery(
                methodName = "add",
                canister = canister
            )

            val result = icpQuery.callAndPoll(
                values = listOf(
                    ValueToEncode(
                        arg = trusted_source,
                        expectedClass = ICPPrincipalApiModel::class,
                        expectedClassNullable = true
                    ),
                    ValueToEncode(nft)
                ),
                sender = sender,
                pollingValues = pollingValues,
            ).getOrThrow()

            return CandidDecoder.decodeNotNull(result.first())
        }

        suspend fun remove                    (
            trusted_source: ICPPrincipalApiModel?,
            nft_id: ICPPrincipalApiModel,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): operation_response {

            val icpQuery = ICPQuery(
                methodName = "remove",
                canister = canister
            )

            val result = icpQuery.callAndPoll(
                values = listOf(
                    ValueToEncode(
                        arg = trusted_source,
                        expectedClass = ICPPrincipalApiModel::class,
                        expectedClassNullable = true
                    ),
                    ValueToEncode(nft_id)),
                sender = sender,
                pollingValues = pollingValues,
            ).getOrThrow()

            return CandidDecoder.decodeNotNull(result.first())
        }

        // Canister methods
        suspend fun get_all         (
            certification: ICPRequestCertification = ICPRequestCertification.Uncertified,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): Array<nft_canister> {

            val icpQuery = ICPQuery(
                methodName = "get_all",
                canister = canister
            )

            val result = icpQuery.invoke(
                values = null,
                sender = sender,
                pollingValues = pollingValues,
                certification = certification
            ).getOrThrow()

            return CandidDecoder.decodeNotNull(result.first())
        }

        suspend fun add_admin                    (
            admin: ICPPrincipalApiModel,
            sender: ICPSigningPrincipal? = null,
            pollingValues: PollingValues = PollingValues()
        ): operation_response {

            val icpQuery = ICPQuery(
                methodName = "add_admin",
                canister = canister
            )

            val result = icpQuery.callAndPoll(
                values = listOf(
                    ValueToEncode(admin)
                ),
                sender = sender,
                pollingValues = pollingValues,
            ).getOrThrow()

            return CandidDecoder.decodeNotNull(result.first())
        }
    }
}