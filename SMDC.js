'use strict';
const express  = require("express");
const router  = express.Router();
const {Sdk, Options} = require("@unique-nft/sdk")
const {generateAccount, SignatureType, getAccountFromMnemonic, Accounts } = require("@unique-nft/accounts");
const {successResponse, errorResponse} = require('./lib/response');
const mnemonic = process.env.SEED;
const KeyringProvider =  require("@unique-nft/accounts/keyring");
const PolkadotProvider  = require("@unique-nft/accounts/polkadot");
const  KeyringOptions =  require("@polkadot/keyring/types");
const OPAL_NETWORK = 'https://rest.unique.network/opal/v1';
const KeyringLocalProvider = require("@unique-nft/accounts/keyring-local");
const options = new KeyringOptions({
    type: 'sr25519',
    });
//const accounts = new Accounts();
const logStruct = (func, error) => {
    return {'func': func, 'file': 'SMDC', error}
  }

const createAccount = async(name) => {
    try { 
    const account = await generateAccount({
        pairType: SignatureType.Sr25519,
        meta: {
          name: name
        }
      })
      return  successResponse(200, account)
   } catch(error){
      console.error('error -> ', logStruct('createAccount', error))
      return errorResponse(error.status, error.message);
   }
  }

router.post('/create', async (req, res) => {
    const response = await createAccount(req.body.email)
    return res.status(response.status).send(response)
  })

const importAccount = async(mnemonic) => {
    try { 
            const account = await getAccountFromMnemonic({
            mnemonic: mnemonic  
            })
        return  successResponse(200, account)
   } catch(error){
      console.error('error -> ', logStruct('importAccount', error))
      return errorResponse(error.status, error.message);
   }
 }

 router.post('/import', async (req, res) => {
    const response = await importAccount(req.body.mnemonic);
    return res.status(response.status).send(response)
  })

  


const getBalance = async(address) => {
    try { 
        const provider = new KeyringProvider(options);
        await provider.init();
        const signer1 = provider.addSeed(mnemonic);
        const clientOptions = new Options ({
            baseUrl:OPAL_NETWORK
        });
        
       // const accounts = new Accounts();
        //await accounts.addProvider(KeyringLocalProvider);
       // await accounts.addProvider(PolkadotProvider);
       // const accountsList = await accounts.getAccounts();
        //console.log(accountsList);
        const sdk = new Sdk(clientOptions);
      // const signer2 = provider.addKeyfile('<json keyfile>');
      const balance = await sdk.balance.get({ address: address});
      return  successResponse(200, balance)
   } catch(error){
      console.error('error -> ', logStruct('getBalance', error))
      return errorResponse(error.status, error.message);
   }
 }

 router.post('/balance', async (req, res) => {
    const response = await getBalance(req.body.address);
    return res.status(response.status).send(response)
  })




