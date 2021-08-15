const app = require('./app')
const client = require('./database/db_connect')
const request = require('supertest')
const { expect, afterAll } = require('@jest/globals')

describe("GET /api/servers/instances/:user", () => {

    describe("when given correct user", () => {
        test("shoud return response 200 and array", async() => {
            const response = await request(app).get('/api/servers/instances/milanovicaleX77@gmail.com')
            expect(response.statusCode).toBe(200)
        })
    })
})

describe("POST /api/servers/instances/add", () => {

    describe("when given correct json object", () => {
        test("shoud return response 201 and message", async() => {
            const response = await request(app).post('/api/servers/instances/add').send({
                "user": "milanovicaleX77@gmail.com",
                "memory": 4,
                "status": true,
                "name": "Linux Ubuntu",
                "type": "Web Server",
                "ipaddress": "192.34.64.2"
            })
            expect(response.statusCode).toBe(201)
        })
    })

    describe("when given incorrect json object", () => {
        test("shoud return response 400 and error message", async() => {
            const response = await request(app).post('/api/servers/instances/add').send({
                "user": "milanovicaleX77@gmail.com",
                "memory": 1,
                "status": true,
                "name": "Linux Ubuntu",
                "type": "Web Server",
                //"ipaddress": "192.111.12.89"
            })
            expect(response.statusCode).toBe(400)
        })
    })
})

describe("DELETE /api/servers/instances/remove/:id", () => {

    describe("when given id", () => {
        test("shoud return response 204", async() => {
            const response = await request(app).delete('/api/servers/instances/remove/4e51c6f6-7f00-420f-8a14-94afc525c1e5')
            expect(response.statusCode).toBe(204)
        })
    })
})

describe("UPDATE /api/servers/instances/update/:id", () => {

    describe("when given existing id toggle status", () => {
        test("shoud return response 200", async() => {
            const response = await request(app).put('/api/servers/instances/update/7c4f663d-fad7-417d-b2f1-bf67c799e326')
            expect(response.statusCode).toBe(200)
        })
    })

    describe("when given non-existing id", () => {
        test("shoud return response 404", async() => {
            const response = await request(app).put('/api/servers/instances/update/7c4f663d-fad7-417d-b2f1-bf67c799e327')
            expect(response.statusCode).toBe(404)
        })
    })
})

afterAll(async () => {
    await client.shutdown()
})
