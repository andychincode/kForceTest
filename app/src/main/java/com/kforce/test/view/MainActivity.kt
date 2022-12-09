package com.kforce.test.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kforce.test.R
import com.kforce.test.di.Injection
import com.kforce.test.model.AcronymData
import com.kforce.test.view.adapter.AcronymListAdapter
import com.kforce.test.viewmodel.AcronymsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AcronymsViewModel
    private lateinit var adapter: AcronymListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // checking network connection
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(
            builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {    // network connected
                    runOnUiThread {
                        print(getString(R.string.network_connected))
                        onResume()
                    }
                }

                override fun onLost(network: Network) {     // network connection fail
                    Snackbar.make(
                        contentView,
                        getString(R.string.network_unavailable),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        )

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        txtAcronym.addTextChangedListener {
            val text = it.toString()
            if (text.length >= 2) {
                progressBar.visibility = View.VISIBLE
                viewModel.loadAcronyms(text)
            } else {
                this.adapter.update(emptyList())
            }
        }

        adapter = AcronymListAdapter(emptyList())
        rvWords.layoutManager = LinearLayoutManager(this)
        rvWords.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()) [AcronymsViewModel::class.java]

        viewModel.acronyms.observe(this, onAcronymsObserver)
        viewModel.error.observe(this, onErrorObserver)
    }

    private val onAcronymsObserver = Observer<List<AcronymData>> {
        progressBar.visibility = View.GONE

        if (it.isNotEmpty()) {
            val words = it[0].longforms
            this.adapter.update(words)
        } else {
            this.adapter.update(emptyList())
        }
    }

    private val onErrorObserver = Observer<String?> {
        print(it)

        Snackbar.make(
            contentView,
            getString(R.string.error_fetch_acronyms),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}





