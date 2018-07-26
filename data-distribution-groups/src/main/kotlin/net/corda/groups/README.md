# Staged approach for introducing DDGs

## Stage one.

* Groups currently have a name and key. The name is just a string.

* the confidential identities infrastructure is used to create a key
  and certificate for the group.

* Groups are stored as ContractStates in the vault.

* The assumption at stage one is that only the creator of the group can
  write to it.

* Multiple states for the same data distribution group can be stored in
  the vault. Each state stores a bi-lateral group relationship apart
  from the initial group state which just contains the group founder.

* Only the founder can add transactions to the group.

* Transactions are signed with the group key by the founder.

* Invitees check the signature on new transactions added to the group.
  They store all transactions anyway as currently the pre-packaged
  SendTransactionFlow and ReceiveTransactionFlows are used.

* Transactions are propagated on to neighbours until there are no new
  neighbours to send the transactions to.