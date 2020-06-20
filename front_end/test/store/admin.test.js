const describe = require('mocha')
const admin =require('../../src/store/modules/admin')
const expect = require('chai')

describe('set_managerList测试', function () {
    it('managerList应该与传入的data一致', function () {
        admin.set_managerList([1,2,3])
        expect(state.managerList.to.be.equal([1,2,3]))
    })
})