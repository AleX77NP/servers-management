const app = require('./app')
require('dotenv').config()

const PORT = process.env.PORT

app.listen(PORT, () => {
    console.log(`Server listening on at http://localhost:${PORT}`)
})
