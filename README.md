# Problema
Estamos construindo um autorizador de transação, e precisamos criar uma solução que atenda os seguintes requisitos:
- Não permitir transações duplicadas
- Permitir transações apenas se tiver saldo
- Permitir que ocorra escala horizontal, e isso não pode impactar as regras anteriores

## Not Allow Duplicate Entries

All transactions must have an idempotency id (*transactionId*) that is going to be checked before processing it. I develop as AOP, so before the method that process the transaction, will check in a decoupled code, if that *transactionId* already existis in database, throwing an exception in such case.


## Only allow transactions when the account has enough money

Each entry has a *Transaction Type: CREDIT, DEBIT* that indicates wheather to substract or add money to the account balance. So exists a validation in order to check if the transaction has enough money.

## Scale-out

The application must be runned with a distributed lock manager, in order to lock the accounts and allow to scale-out with out any money loss. I implemented *redis* to achieve this, so before each transaction, the account is locked, and will release the lock after finishing the transaction.


