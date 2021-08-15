const express = require('express')
const {createServerInstance, getMyInstances, deleteInstance, updateInstanceStatus} = require('../controllers/serversController')

const router = express.Router()

router.route('/instances/add').post(createServerInstance)
router.route('/instances/:user').get(getMyInstances)
router.route('/instances/remove/:id').delete(deleteInstance)
router.route('/instances/update/:id').put(updateInstanceStatus)

module.exports = router
