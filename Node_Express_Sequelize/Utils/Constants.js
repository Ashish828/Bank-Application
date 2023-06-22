const SELECT_BANK = "SELECT * FROM bank";
const SELECT_ACCOUNT = "SELECT * FROM account";
const SELECT_CUSTOMER = "SELECT * FROM customer";
const SELECT_TRANSACTION = "SELECT * FROM transaction";

const INSERT_INTO_ACCOUNT = (balance, pin, bankId, customerId) => `INSERT INTO account(balance, pin, bank_id, customer_id) VALUES (${balance}, ${pin}, ${bankId}, ${customerId})`;
const INSERT_INTO_CUSTOMER = (first_name, last_name, email, address, password) => `INSERT INTO customer(first_name, last_name, email, address, password) VALUES ('${first_name}', '${last_name}', '${email}', '${address}', ${password})`;
const INSERT_INTO_TRANSACTION = (accountId, balance, amount, type) => `INSERT INTO transaction(account_id, balance, amount, type, date) VALUES (${accountId}, ${balance}, ${amount}, '${type}', '${new Date().toISOString().slice(0, 19).replace('T', ' ')}')`;

const JSON_MESSAGE = (message) => { message };

const DEBITED = "DEBITED";
const CREDITED = "CREDITED";

module.exports = {
    SELECT_BANK,
    SELECT_ACCOUNT,
    SELECT_CUSTOMER,
    SELECT_TRANSACTION,
    DEBITED,
    CREDITED,
    INSERT_INTO_ACCOUNT,
    INSERT_INTO_CUSTOMER,
    INSERT_INTO_TRANSACTION,
    JSON_MESSAGE
}