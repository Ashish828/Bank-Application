var DataTypes = require("sequelize").DataTypes;
var _account = require("./account");
var _bank = require("./bank");
var _customer = require("./customer");
var _transaction = require("./transaction");

function initModels(sequelize) {
  var account = _account(sequelize, DataTypes);
  var bank = _bank(sequelize, DataTypes);
  var customer = _customer(sequelize, DataTypes);
  var transaction = _transaction(sequelize, DataTypes);

  transaction.belongsTo(account, { as: "account", foreignKey: "account_id"});
  account.hasMany(transaction, { as: "transactions", foreignKey: "account_id"});
  account.belongsTo(bank, { as: "bank", foreignKey: "bank_id"});
  bank.hasMany(account, { as: "accounts", foreignKey: "bank_id"});
  account.belongsTo(customer, { as: "customer", foreignKey: "customer_id"});
  customer.hasMany(account, { as: "accounts", foreignKey: "customer_id"});

  return {
    account,
    bank,
    customer,
    transaction,
  };
}
module.exports = initModels;
module.exports.initModels = initModels;
module.exports.default = initModels;
