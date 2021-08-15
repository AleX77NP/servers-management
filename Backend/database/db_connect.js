const { Client } = require('cassandra-driver');

const PATH = `${process.env.PWD}` + '/.env'

require('dotenv').config({ path: PATH})

const client = new Client({
    cloud: {
      secureConnectBundle: `${process.cwd()}/SB.zip`,
    },
    credentials: {
      username: process.env.CLIENT_ID,
      password: process.env.CLIENT_SECRET,
    },
});

module.exports = client