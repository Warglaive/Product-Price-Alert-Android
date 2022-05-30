"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.UserController = void 0;
const tslib_1 = require("tslib");
//import { User } from '@loopback/authentication-jwt/dist/models';
const repository_1 = require("@loopback/repository");
const rest_1 = require("@loopback/rest");
const models_1 = require("../models");
const repositories_1 = require("../repositories");
let UserController = class UserController {
    constructor(userRepository) {
        this.userRepository = userRepository;
    }
    async create(user) {
        return this.userRepository.create(user);
    }
    async count(where) {
        return this.userRepository.count(where);
    }
    async find(filter) {
        return this.userRepository.find(filter);
    }
    async updateAll(user, where) {
        return this.userRepository.updateAll(user, where);
    }
    /**
      * changed to string
      */
    async findById(id, filter) {
        return this.userRepository.findById(id, filter);
    }
    async updateById(id, user) {
        await this.userRepository.updateById(id, user);
    }
    async replaceById(id, user) {
        await this.userRepository.replaceById(id, user);
    }
    async deleteById(id) {
        await this.userRepository.deleteById(id);
    }
    async login(email) {
        return this.userRepository.login(email);
    }
};
(0, tslib_1.__decorate)([
    (0, rest_1.post)('/users'),
    (0, rest_1.response)(200, {
        description: 'User model instance',
        content: { 'application/json': { schema: (0, rest_1.getModelSchemaRef)(models_1.User) } },
    }),
    (0, tslib_1.__param)(0, (0, rest_1.requestBody)({
        content: {
            'application/json': {
                schema: (0, rest_1.getModelSchemaRef)(models_1.User, {
                    title: 'NewUser',
                    exclude: ['id'],
                }),
            },
        },
    })),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [Object]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "create", null);
(0, tslib_1.__decorate)([
    (0, rest_1.get)('/users/count'),
    (0, rest_1.response)(200, {
        description: 'User model count',
        content: { 'application/json': { schema: repository_1.CountSchema } },
    }),
    (0, tslib_1.__param)(0, rest_1.param.where(models_1.User)),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [Object]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "count", null);
(0, tslib_1.__decorate)([
    (0, rest_1.get)('/users'),
    (0, rest_1.response)(200, {
        description: 'Array of User model instances',
        content: {
            'application/json': {
                schema: {
                    type: 'array',
                    items: (0, rest_1.getModelSchemaRef)(models_1.User, { includeRelations: true }),
                },
            },
        },
    }),
    (0, tslib_1.__param)(0, rest_1.param.filter(models_1.User)),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [Object]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "find", null);
(0, tslib_1.__decorate)([
    (0, rest_1.patch)('/users'),
    (0, rest_1.response)(200, {
        description: 'User PATCH success count',
        content: { 'application/json': { schema: repository_1.CountSchema } },
    }),
    (0, tslib_1.__param)(0, (0, rest_1.requestBody)({
        content: {
            'application/json': {
                schema: (0, rest_1.getModelSchemaRef)(models_1.User, { partial: true }),
            },
        },
    })),
    (0, tslib_1.__param)(1, rest_1.param.where(models_1.User)),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [models_1.User, Object]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "updateAll", null);
(0, tslib_1.__decorate)([
    (0, rest_1.get)('/users/{id}'),
    (0, rest_1.response)(200, {
        description: 'User model instance',
        content: {
            'application/json': {
                schema: (0, rest_1.getModelSchemaRef)(models_1.User, { includeRelations: true }),
            },
        },
    }),
    (0, tslib_1.__param)(0, rest_1.param.path.string('id')),
    (0, tslib_1.__param)(1, rest_1.param.filter(models_1.User, { exclude: 'where' })),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [String, Object]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "findById", null);
(0, tslib_1.__decorate)([
    (0, rest_1.patch)('/users/{id}'),
    (0, rest_1.response)(204, {
        description: 'User PATCH success',
    }),
    (0, tslib_1.__param)(0, rest_1.param.path.string('id')),
    (0, tslib_1.__param)(1, (0, rest_1.requestBody)({
        content: {
            'application/json': {
                schema: (0, rest_1.getModelSchemaRef)(models_1.User, { partial: true }),
            },
        },
    })),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [String, models_1.User]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "updateById", null);
(0, tslib_1.__decorate)([
    (0, rest_1.put)('/users/{id}'),
    (0, rest_1.response)(204, {
        description: 'User PUT success',
    }),
    (0, tslib_1.__param)(0, rest_1.param.path.string('id')),
    (0, tslib_1.__param)(1, (0, rest_1.requestBody)()),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [String, models_1.User]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "replaceById", null);
(0, tslib_1.__decorate)([
    (0, rest_1.del)('/users/{id}'),
    (0, rest_1.response)(204, {
        description: 'User DELETE success',
    }),
    (0, tslib_1.__param)(0, rest_1.param.path.string('id')),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [String]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "deleteById", null);
(0, tslib_1.__decorate)([
    (0, rest_1.get)('/users/login/{email}'),
    (0, rest_1.response)(200, {
        description: 'User model instance',
        content: {
            'application/json': {
                schema: (0, rest_1.getModelSchemaRef)(models_1.User, { includeRelations: true }),
            },
        },
    }),
    (0, tslib_1.__param)(0, rest_1.param.path.string('email')),
    (0, tslib_1.__metadata)("design:type", Function),
    (0, tslib_1.__metadata)("design:paramtypes", [String]),
    (0, tslib_1.__metadata)("design:returntype", Promise)
], UserController.prototype, "login", null);
UserController = (0, tslib_1.__decorate)([
    (0, tslib_1.__param)(0, (0, repository_1.repository)(repositories_1.UserRepository)),
    (0, tslib_1.__metadata)("design:paramtypes", [repositories_1.UserRepository])
], UserController);
exports.UserController = UserController;
//getId, getIdObject, toJSON, toObject
//# sourceMappingURL=user.controller.js.map