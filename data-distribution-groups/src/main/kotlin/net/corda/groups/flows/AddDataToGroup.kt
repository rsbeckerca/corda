package net.corda.groups.flows

import co.paralleluniverse.fibers.Suspendable
import net.corda.core.crypto.Crypto
import net.corda.core.crypto.SignableData
import net.corda.core.crypto.SignatureMetadata
import net.corda.core.flows.FlowLogic
import net.corda.core.transactions.SignedTransaction
import java.security.PublicKey

class AddDataToGroup(val key: PublicKey, val transactions: Set<SignedTransaction>) : FlowLogic<Unit>() {
    @Suspendable
    override fun call() {
        transactions.forEach { transaction ->
            logger.info("Adding transaction ${transaction.id} to group $key.")

            val signatureMetadata = SignatureMetadata(
                    platformVersion = serviceHub.myInfo.platformVersion,
                    schemeNumberID = Crypto.findSignatureScheme(key).schemeNumberID
            )

            val signableData = SignableData(transaction.id, signatureMetadata)
            val sig = serviceHub.keyManagementService.sign(signableData, key)

            sig.verify(transaction.id)

            // Add the signature and then send the transaction to the group.
            val transactionToAdd = transaction.withAdditionalSignature(sig)
            subFlow(SendDataToGroup(key, transactionToAdd))
        }
    }
}