package net.corda.groups.flows

import net.corda.testing.node.internal.TestStartedNode
import org.junit.Before

class DataDistributionGroupTests : MockNetworkTest(numberOfNodes = 5) {

    lateinit var A: TestStartedNode
    lateinit var B: TestStartedNode
    lateinit var C: TestStartedNode
    lateinit var D: TestStartedNode
    lateinit var E: TestStartedNode

    @Before
    override fun initialiseNodes() {
        A = nodes[1]
        B = nodes[2]
        C = nodes[3]
        D = nodes[4]
        E = nodes[5]
    }

    fun TestStartedNode.createGroup() {
        A.services.startFlow()
    }

}