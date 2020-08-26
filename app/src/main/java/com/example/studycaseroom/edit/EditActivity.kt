package com.example.studycaseroom.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studycaseroom.database.Item
import com.example.studycaseroom.database.ItemDatabase
import com.example.studycaseroom.R
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), EditActivityPresenter.Listener {

    private lateinit var presenter: EditActivityPresenter
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }
        ItemDatabase.getInstance(this)?.let {
            presenter = EditActivityPresenter(it, this)
        }

        et_nama_edit.setText(item.name)
        et_quantity_edit.setText(item.quantity.toString())

        btn_save_edit.setOnClickListener {
            item.apply {
                name = et_nama_edit.text.toString()
                quantity = et_quantity_edit.text.toString().toInt()
            }
            presenter.editItem(item)
        }
    }

    override fun showEditSuccess() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Telah Terupdate", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    override fun showEditFailed() {
        runOnUiThread {
            Toast.makeText(this@EditActivity,"Data Gagal diupdate", Toast.LENGTH_LONG).show()
        }
    }
}