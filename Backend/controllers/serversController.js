const client = require('../database/db_connect')
const { v4: uuidv4 } = require('uuid');

// @desc Create new instance
// @route POST /api/instances/add
// @access Private 
const createServerInstance = async(req, res) => {
    try {
        const id = uuidv4()
        const { user, ipaddress, memory, name, status, type } = req.body
        if (!user || !ipaddress || !memory || !name || !status || !type) {
            return res.status(400).json({'error': 'Invalid info.'})
        }
        const result = await client.execute(`INSERT INTO servers.instances (id, user, ipaddress, memory, name, status, type)
        VALUES (${id}, '${user}', '${ipaddress}', ${memory}, '${name}', ${status}, '${type}');`)

        res.status(201).json({'message': 'Instance created.'})
        console.log(result.info)
    } catch(e) {
        res.status(500).json({'error': 'An error occurred.'})
        console.log(e)
    }
}


// @desc Get user's instances
// @route GET /api/instances/:user
// @access Private 
const getMyInstances = async(req, res) => {
    try {
        const result = await client.execute(`SELECT * FROM servers.instances WHERE user='${req.params.user}';`)
        res.json(result.rows)
    } catch(e) {
        res.status(500).json({'error': 'An error occurred.'})
        console.log(e)
    }
}


// @desc Delete user's instance
// @route DELETE /api/instances/remove/:id
// @access Private
const deleteInstance = async(req, res) => {
    try {
        const id = req.params.id
        const result = await client.execute(`DELETE FROM servers.instances WHERE id=${id};`)
        console.log(result.info)
        res.status(204).json({})
    } catch(e) {
        res.status(500).json({'error': 'An error occurred.'})
        console.log(e)
    }
}


// @desc Update instance status
// @desc PUT /api/instance/update/:id
// @access Private
const updateInstanceStatus = async(req, res) => {
    try {
        const id = req.params.id
        const results = await client.execute(`SELECT status FROM servers.instances WHERE id=${id} LIMIT 1;`)
        if(results.rows[0] === undefined) {
            return res.status(404).json({'message' : "Server not found"})
        }
        currentStatus = !results.rows[0].status
        const result = await client.execute(`UPDATE servers.instances SET status = ${currentStatus} WHERE id=${id};`)
        console.log(result.info)
        res.json({'message': 'Instance updated.'})
    } catch(e) {
        res.status(500).json({'error': 'An error occurred.'})
        console.log(e)
    }
}

module.exports = {createServerInstance, getMyInstances, deleteInstance, updateInstanceStatus}