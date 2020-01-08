package org.quarkchain.web3j.protocol.core;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.text.html.InlineView;

import org.quarkchain.web3j.protocol.Web3j;
import org.quarkchain.web3j.protocol.Web3jService;
import org.quarkchain.web3j.protocol.core.request.EthFilter;
import org.quarkchain.web3j.protocol.core.request.TransactionReq;
import org.quarkchain.web3j.protocol.core.response.EstimateGas;
import org.quarkchain.web3j.protocol.core.response.EthLog;
import org.quarkchain.web3j.protocol.core.response.EthTransaction;
import org.quarkchain.web3j.protocol.core.response.GasPrice;
import org.quarkchain.web3j.protocol.core.response.GetAccountData;
import org.quarkchain.web3j.protocol.core.response.GetBalances;
import org.quarkchain.web3j.protocol.core.response.GetTransactionCount;
import org.quarkchain.web3j.protocol.core.response.GetTransactionReceipt;
import org.quarkchain.web3j.protocol.core.response.GetMinorBlock;
import org.quarkchain.web3j.protocol.core.response.NetworkInfo;
import org.quarkchain.web3j.protocol.core.response.GetRootBlock;
import org.quarkchain.web3j.protocol.core.response.SendTransaction;
import org.quarkchain.web3j.utils.Numeric;
import org.quarkchain.web3j.protocol.core.response.Call;

/**
 * JSON-RPC 2.0 factory implementation.
 */
public class JsonRpcWeb3j implements Web3j {

	protected static final long ID = 1;

	protected Web3jService web3jService;

	public JsonRpcWeb3j(Web3jService web3jService) {
		this.web3jService = web3jService;
	}

	@Override
	public Request<?, NetworkInfo> networkInfo() {
		return new Request<>("networkInfo", Collections.<String>emptyList(), ID, web3jService, NetworkInfo.class);
	}

	@Override
	public Request<?, GasPrice> gasPrice(String fullShardKey, String tokenID) {
		return new Request<>("gasPrice", Arrays.asList(fullShardKey, tokenID), ID, web3jService, GasPrice.class);
	}

	@Override
	public Request<?, GetBalances> getBalances(String address) {
		return new Request<>("getBalances", Arrays.asList(address), ID, web3jService, GetBalances.class);
	}

	@Override
	public Request<?, GetTransactionCount> getTransactionCount(String address) {
		return new Request<>("getTransactionCount", Arrays.asList(address), ID, web3jService,
				GetTransactionCount.class);
	}

	@Override
	public Request<?, SendTransaction> sendTransaction(TransactionReq transaction) {
		return new Request<>("sendTransaction", Arrays.asList(transaction), ID, web3jService, SendTransaction.class);
	}

	@Override
	public Request<?, SendTransaction> sendRawTransaction(String signedTransactionData) {
		return new Request<>("sendRawTransaction", Arrays.asList(signedTransactionData), ID, web3jService,
				SendTransaction.class);
	}

	@Override
	public Request<?, Call> call(TransactionReq transaction, DefaultBlockParameter defaultBlockParameter) {
		return new Request<>("call", Arrays.asList(transaction, defaultBlockParameter), ID, web3jService, Call.class);
	}

	@Override
	public Request<?, EstimateGas> estimateGas(TransactionReq transaction) {
		return new Request<>("estimateGas", Arrays.asList(transaction), ID, web3jService, EstimateGas.class);
	}

	@Override
	public Request<?, GetRootBlock> getRootBlockByHeight(BigInteger blockNumber) {
		return new Request<>("getRootBlockByHeight", Arrays.asList(Numeric.toHexStringWithPrefix(blockNumber)), ID,
				web3jService, GetRootBlock.class);
	}

	@Override
	public Request<?, GetRootBlock> getRootBlockById(String blockId) {
		return new Request<>("getRootBlockById", Arrays.asList(blockId), ID, web3jService, GetRootBlock.class);
	}

	@Override
	public Request<?, GetMinorBlock> getMinorBlockByHeight(String fullShardId, BigInteger blockNumber, boolean withTx) {
		return new Request<>("getMinorBlockByHeight",
				Arrays.asList(fullShardId, Numeric.toHexStringWithPrefix(blockNumber), withTx), ID, web3jService,
				GetMinorBlock.class);
	}

	@Override
	public Request<?, GetMinorBlock> getMinorBlockById(String blockId, boolean withTx) {
		return new Request<>("getMinorBlockById", Arrays.asList(blockId, withTx), ID, web3jService,
				GetMinorBlock.class);
	}

	@Override
	public Request<?, EthTransaction> getTransactionById(String transactionHash) {
		return new Request<>("getTransactionById", Arrays.asList(transactionHash), ID, web3jService,
				EthTransaction.class);
	}

	@Override
	public Request<?, GetTransactionReceipt> getTransactionReceipt(String transactionHash) {
		return new Request<>("getTransactionReceipt", Arrays.asList(transactionHash), ID, web3jService,
				GetTransactionReceipt.class);
	}

	@Override
	public Request<?, EthLog> getLogs(EthFilter ethFilter) {
		return new Request<>("getLogs", Arrays.asList(ethFilter), ID, web3jService, EthLog.class);
	}

	@Override
	public Request<?, GetAccountData> getAccountData(String address, DefaultBlockParameter defaultBlockParameter,
			boolean includeOtherShards) {
		return new Request<>("getAccountData", Arrays.asList(address, defaultBlockParameter, includeOtherShards), ID,
				web3jService, GetAccountData.class);
	}

}