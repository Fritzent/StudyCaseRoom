package com.example.studycaseroom.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studycaseroom.add.AddActivity
import com.example.studycaseroom.ItemAdapter
import com.example.studycaseroom.database.ItemDatabase
import com.example.studycaseroom.R
import com.example.studycaseroom.database.Item
import com.example.studycaseroom.edit.EditActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {

    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ItemDatabase.getInstance(this)?.let {
            presenter = MainActivityPresenter(it, this)
        }

        fab_main.setOnClickListener {
            presenter.goToAddActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchData()
    }
    override fun showItemList(listItem: List<Item>) {
        runOnUiThread {
            val adapter = ItemAdapter(listItem, presenter)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun goToAddActivity() {
        val intentGoto = Intent(this, AddActivity::class.java)
        startActivity(intentGoto)
    }

    override fun goToEditActivity(item: Item) {
        val intentToEdit = Intent(this, EditActivity::class.java)
        intentToEdit.putExtra("item", item)
        startActivity(intentToEdit)
    }

    override fun showDeletedSuccess(item: Item) {
        runOnUiThread {
            Toast.makeText(this, "Data ${item.name} Telah dihapus", Toast.LENGTH_LONG).show()
            presenter.fetchData()
        }
    }

    override fun showDeletedFailed(item: Item) {
        runOnUiThread {
            Toast.makeText(this, "Data ${item.name} Gagal dihapus", Toast.LENGTH_LONG).show()
        }
    }
}