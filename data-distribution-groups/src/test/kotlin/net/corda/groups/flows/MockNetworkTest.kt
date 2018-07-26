package net.corda.groups.flows

import net.corda.core.identity.CordaX500Name
import net.corda.testing.node.internal.InternalMockNetwork
import net.corda.testing.node.internal.TestStartedNode
import net.corda.testing.node.internal.cordappsForPackages
import org.junit.After
import org.junit.Before

abstract class MockNetworkTest(val numberOfNodes: Int) {

    protected val network = InternalMockNetwork(
            cordappsForAllNodes = cordappsForPackages("net.corda.groups"),
            threadPerNode = true
    )

    protected lateinit var nodes: List<TestStartedNode>

    @Before
    abstract fun initialiseNodes()

    @Before
    fun setupNetwork() {
        nodes = createSomeNodes(numberOfNodes)
    }

    @After
    fun tearDownNetwork() {
        network.stopNodes()
    }

    private fun createSomeNodes(numberOfNodes: Int = 2): List<TestStartedNode> {
        val partyNodes = (1..numberOfNodes).map { current ->
            val char = current.toChar() + 64
            val name = CordaX500Name("Party$char", "London", "GB")
            network.createPartyNode(name)
        }
        return partyNodes
    }
}