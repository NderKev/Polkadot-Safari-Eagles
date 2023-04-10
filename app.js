const express = require('express');
const cors = require('cors');

require('dotenv').config();

const app = express();

app.use(cors());

app.use(express.urlencoded({extended: true})); 
app.use(express.json());

app.use(function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    next();
});

const smdcRoute = require('./SMDC');
 app.use('/smdc/v1', smdcRoute);



 // catch 404 and forward to error handler
 app.use((req, res, next) => {
    res.status(404).send({
      success : false,
      message : 'notFound',
      type : 'SmartMedicare BD',
      action: req.method+' '+req.originalUrl,
      data : [],
      meta:{}
    });
  });
  
  // error handler
  app.use((err, req, res, next) => {
    if(err && err.status==520){
      return next();
    }
    console.error({
      type : 'uncaughtException',
      err:err
    }, 'doeremi uncaughtException');
    res.status(520).send({
      success : false,
      message : 'somethingWentWrong',
      type : 'SmartMedicare BD',
      action: 'uncaughtException'
    });
  });
  
  
  module.exports = app;