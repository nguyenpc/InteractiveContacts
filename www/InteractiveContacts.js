var exec = require('cordova/exec');

function InteractiveContacts() {}

InteractiveContacts.prototype.addContact = function(contact, success, error) {
    exec(success, error, "InteractiveContacts", "addContact", [contact]);
};

module.exports = new InteractiveContacts();
