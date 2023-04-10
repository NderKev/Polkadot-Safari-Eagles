'use strict';
const express  = require("express");
const router  = express.Router();

const {generateAccount, SignatureType, getAccountFromMnemonic, Accounts } = require("@unique-nft/accounts");
const {successResponse, errorResponse} = require('./lib/response');
const mnemonic = process.env.SEED;
const KeyringProvider =  require("@unique-nft/accounts/keyring");
const PolkadotProvider  = require("@unique-nft/accounts/polkadot");

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
        //const provider = new KeyringProvider(options);
        //await provider.init();
        //const signer1 = provider.addSeed(data.mnemonic);
        const {Sdk, Options} = require("@unique-nft/sdk")
        const clientOptions = {
            baseUrl:OPAL_NETWORK,
        }; 
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


  /** const createCollection = async(data) => {
    try { 
        const {Sdk} = require("@unique-nft/substrate-client");
        const {
         COLLECTION_SCHEMA_NAME,
         CreateCollectionNewArguments,
        } =  require("@unique-nft/substrate-client/tokens");

        const { UniqueCollectionSchemaToCreate } = require("@unique-nft/api");
        const provider = new KeyringProvider(options);
        await provider.init();
        //const signer1 = provider.addSeed(mnemonic);
        const clientOptions = new Options ({
            baseUrl: OPAL_NETWORK
        });
        
        const collectionSchema = new UniqueCollectionSchemaToCreate({
            schemaName: COLLECTION_SCHEMA_NAME.unique,
            schemaVersion: "1.0.0",
            image: { urlTemplate: "some_url/{infix}.extension" },
            coverPicture: {
                ipfsCid: "<valid_ipfs_cid>",
            },
        });
    
        // Fill up required arguments
        const args = new CreateCollectionNewArguments ({
            address: data.address,
            name: data.name,
            description: data.name,
            tokenPrefix: data.prefix,
            schema: collectionSchema,
        });
       // const accounts = new Accounts();
        //await accounts.addProvider(KeyringLocalProvider);
       // await accounts.addProvider(PolkadotProvider);
       // const accountsList = await accounts.getAccounts();
        //console.log(accountsList);
        const sdk = new Sdk(clientOptions);
      // const signer2 = provider.addKeyfile('<json keyfile>');
      //const balance = await sdk.balance.get({ address: address});
      return  successResponse(200, balance)
   } catch(error){
      console.error('error -> ', logStruct('createCollection', error))
      return errorResponse(error.status, error.message);
   }
 } **/

 const createNFTCollection = async(data) => {
  try { 
  const Sdk = require("@unique-nft/substrate-client");
    const {
     COLLECTION_SCHEMA_NAME,
     CreateCollectionNewArguments,
    } =  require("@unique-nft/substrate-client/tokens");

    const chainWsUrl = 'wss://ws-opal.unique.network';
    const signer = await KeyringProvider.fromMnemonic(data.mnemonic);
    const sdk = new Sdk({
      chainWsUrl,
      signer,
    });
  const createNFTDir = await sdk.collections.creation.submitWaitResult({
  address : signer.getAddress(),
  name: data.name,
  description: data.desc,
  tokenPrefix: 'SMC',
  schema: {
    schemaName: COLLECTION_SCHEMA_NAME.unique,
    schemaVersion: '1.0.0',
    coverPicture: {
      ipfsCid: data.cid,
    },
    image: {
      urlTemplate: 'https://ipfs.unique.network/ipfs/{infix}',
    },
    attributesSchemaVersion: '1.0.0',
    attributesSchema: {
      0: { name: { '_': 'name' },
      isArray: false,
      optional: false,
      type: AttributeType.string,
      },
      1: {
        name: { '_': 'type' },
        type: AttributeType.string,
        optional: true,
        isArray: false,
        enumValues: {
          0: { '_': 'doctor' },
          1: { '_': 'patient' }
        },
      },
      2: {
        name: { '_': 'DOB' },
        type: AttributeType.string,
        optional: true,
        isArray: true,
        values: {
          0: { '_': 'day' },
          1: { '_': 'month' },
          2: { '_': 'year' },
        },
      },
      3: {
        name: { '_': 'sex' },
        type: AttributeType.string,
        optional: true,
        isArray: false,
        enumValues: {
          0: { '_': 'male' },
          1: { '_': 'female' }
        },
      },
      4: {
        name: { '_': 'telephone' },
        isArray: false,
        optional: false,
        type: AttributeType.string,
      },
      5: {
        name: { '_': 'bloodtype' },
        isArray: true,
        type: AttributeType.string,
        optional: true,
        enumValues: {
          0: { '_': 'A' },
          1: { '_': 'B' },
          2: { '_': 'AB' },
          3: { '_': '0' },
        },
        },
      6: {
          name: { '_': 'rhesus' },
          type: AttributeType.string,
          optional: true,
          isArray: false,
          enumValues: {
            0: { '_': 'positive' },
            1: { '_': 'negative' }
          },
        },
      7 : { name: { '_': 'weight' },
        isArray: false,
        optional: false,
        type: AttributeType.string,
      },
      8 : { name: { '_': 'height' },
        isArray: false,
        optional: false,
        type: AttributeType.string,
      },
      9: {
        name: { '_': 'address' },
        type: AttributeType.string,
        optional: true,
        isArray: true,
        values: {
          0: { '_': 'street' },
          1: { '_': 'city' },
          2: { '_': 'zipcode' },
          3: { '_': 'state' },
          4: { '_': 'country' },
        },
      },
      10: {
        name: { '_': 'consultations' },
        type: AttributeType.string,
        optional: true,
        isArray: true,
        values: {
          0: { '_': 'file_no' },
          1: { '_': 'symptoms_file_url' },
          2: { '_': 'diagnosis_file_url' },
          3: { '_': 'prescription_file_url' },
          5: { '_': 'doctor_id' },
          6: { '_': 'date' },
        },
      },
      11: {
        name: { '_': 'rewards' },
        type: AttributeType.string,
        optional: true,
        isArray: true,
        values: {
          0: { '_': 'referrals' },
          1: { '_': 'consulations' }
        },
      },
    },
  },
})
return  successResponse(200, createNFTDir)
} catch(error){
   console.error('error -> ', logStruct('createNFTCollection', error))
   return errorResponse(error.status, error.message);
}
};

router.post('/collection', async (req, res) => {
  const response = await createNFTCollection(req.body);
  return res.status(response.status).send(response)
})

const createNFT = async(data) => {
  try {
  const Sdk = require("@unique-nft/substrate-client");
  const chainWsUrl = 'wss://ws-opal.unique.network';
  const signer = await KeyringProvider.fromMnemonic(data.mnemonic);
  const sdk = new Sdk({
      chainWsUrl,
      signer,
    });
  const nftData = await sdk.tokens.create.submitWaitResult({
  address: signer.getAddress(),
  collectionId: data.collectionId,
  data: {
    image: {
      ipfsCid: data.cid,
    },
    encodedAttributes: {
      0: { '_': data.name },
      1 : [data.type],
      2: [data.day, data.month, data.year],
      3: data.sex,
      4: { '_': data.phone}, 
      5: data.bloodtype,
      6: data.rhesus,
      7: { '_': data.weight },
      8: { '_': data.height },
      9: [data.street, data.city, data.zipcode,data.state, data.country],
      10: [data.file_no, data.symptoms, data.diagnosis, data.prescription, data.doc, data.date],
      11 : [0, 0]
    },
  }
})
return  successResponse(200, nftData)
} catch(error){
  console.error('error -> ', logStruct('createNFT', error))
  return errorResponse(error.status, error.message);
}
};

router.post('/nft', async (req, res) => {
  const response = await createNFT(req.body);
  return res.status(response.status).send(response)
})

const getCollection = async(collectionId) => {
  try { 
      const {Sdk, Options} = require("@unique-nft/sdk")
      const clientOptions =  {
          baseUrl:OPAL_NETWORK
      };
      
    const sdk = new Sdk(clientOptions);
    const info =  await sdk.collections.get({ collectionId });
    return  successResponse(200, info)
 } catch(error){
    console.error('error -> ', logStruct('getCollection', error))
    return errorResponse(error.status, error.message);
 }
}

router.get('/collection', async (req, res) => {
  const response = await getCollection(req.body.collectionId);
  return res.status(response.status).send(response)
})


const getNFT = async(data) => {
  try { 
      const {Sdk, Options} = require("@unique-nft/sdk")
      const clientOptions =  {
          baseUrl:OPAL_NETWORK
      }; 
      
    const sdk = new Sdk(clientOptions);
    const nftInfo =  await sdk.tokens.get({ collectionId: data.collectionId, tokenId: data.tokenId });
    return  successResponse(200, nftInfo)
 } catch(error){
    console.error('error -> ', logStruct('getCollection', error))
    return errorResponse(error.status, error.message);
 }
}

router.get('/nft', async (req, res) => {
  const response = await getNFT(req.body);
  return res.status(response.status).send(response)
})

