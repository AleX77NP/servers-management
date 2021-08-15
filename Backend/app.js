const express = require('express')

const serverRoutes = require('./routes/serversRoutes')

const app = express()

app.use(express.json())

app.get('/', (req, res) => {
    res.send('Hello from servers api...')
})

app.get('/test', async(req, res) => {
    try {
        const rs = await client.execute("SELECT * FROM system.local;");
        res.json(rs)
    } catch(e) {
        res.json({'error': 'An error occurred.'})
    }
})

app.use('/api/servers', serverRoutes)

module.exports = app