---
title: InteractiveContacts
description: A cordova plugin which provides native interaction while adding or updating contacts
---


# cordova-plugin-interactive-contacts

## Installation

This requires cordova 5.0+ ( current stable v0.0.1 )

    cordova plugin add cordova-plugin-interactive-contacts
    
### Supported Platforms

- Android

## Add Contact
### Usage

```js
   var successFun = function(res){ console.log("success: ", res); };
   var failureFun = function(res){ console.log("failure: ", res); };
   var contact = { 
      name: 'Yogesh', 
      phone: 98789 
   };
   cordova.exec(successFun, failureFun, "InteractiveContacts", "addContact", [contact]);
```    

    
## Update Contact
### Usage

```js
   var successFun = function(res){ console.log("success: ", res); };
   var failureFun = function(res){ console.log("failure: ", res); };
   var contact = { 
      name: 'Yogesh', 
      phone: 98789 
   };
   cordova.exec(successFun, failureFun, "InteractiveContacts", "updateContact", [contact]);
```    
