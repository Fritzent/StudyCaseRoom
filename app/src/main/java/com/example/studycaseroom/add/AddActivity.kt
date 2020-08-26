package com.example.studycaseroom.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studycaseroom.database.Item
import com.example.studycaseroom.database.ItemDatabase
import com.example.studycaseroom.R
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddActivityPresenter.Listener {

    private lateinit var presenter: AddActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        ItemDatabase.getInstance(this)?.let {
            presenter = AddActivityPresenter(it, this)
        }

        btn_save.setOnClickListener {
            val item = Item(
                null,
                et_nama.text.toString(),
                et_quantity.text.toString().toInt()
            )
            presenter.saveItem(item)

        }

    }

    override fun showSaveSuccess() {
        runOnUiThread {
            Toast.makeText(this, "Data telah disimpan", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun showSaveFailed() {
        runOnUiThread {
            Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
        }
    }
}