package com.bity.icp_kotlin_kit.data.service.nft

import com.bity.icp_kotlin_kit.data.generated_file.OrigynNFT
import com.bity.icp_kotlin_kit.domain.model.nft.ICPNFTDetails
import com.bity.icp_kotlin_kit.domain.model.ICPPrincipal
import com.bity.icp_kotlin_kit.domain.model.nft.ICPNFTCollectionItem
import com.bity.icp_kotlin_kit.domain.model.toDataModel
import com.bity.icp_kotlin_kit.domain.repository.NFTRepository
import java.math.BigInteger

open class OrigynNFTRepository(
    private val canister: OrigynNFT.Nft_Canister
): NFTRepository {

    override suspend fun fetchUserHoldings(principal: ICPPrincipal): List<ICPNFTDetails> {
        val account = OrigynNFT.Account.principal(principal.toDataModel())
        val nftIds = when (val nftBalanceResult = canister.balance_of_nft_origyn(account)) {
            is OrigynNFT.BalanceResult.ok -> nftBalanceResult.ok.nfts
            is OrigynNFT.BalanceResult.err -> TODO()
        }
        val nftInfos = canister.nft_batch_origyn(nftIds)
        val results = nftInfos.filterIsInstance<OrigynNFT.NFTInfoResult.ok>()
        val errors = nftInfos.filterIsInstance<OrigynNFT.NFTInfoResult.err>()

        if(errors.isNotEmpty()) TODO()

        return nftIds.zip(results).map(::getNFTDetails)
    }

    override suspend fun fetchIds(
        prev: BigInteger?,
        take: BigInteger?
    ): List<BigInteger> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchNFTs(collectionPrincipal: ICPPrincipal): List<ICPNFTCollectionItem> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchOwner(
        collectionPrincipal: ICPPrincipal,
        nftId: BigInteger,
    ): ICPPrincipal? {
        TODO("Not yet implemented")
    }

    override suspend fun fetchNFT(
        collectionPrincipal: ICPPrincipal,
        nftId: BigInteger,
    ) : ICPNFTCollectionItem {
        TODO("Not yet implemented")
    }

    private fun getNFTDetails(pair: Pair<String, OrigynNFT.NFTInfoResult.ok>): ICPNFTDetails {
        val index = pair.first
        val info = pair.second
        val metadata = info.ok.metadata
        TODO()
    }

}