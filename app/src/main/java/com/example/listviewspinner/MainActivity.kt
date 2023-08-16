package com.example.listviewspinner

import android.os.Bundle
import android.widget.AdapterView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.listviewspinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListViewSimple()
    }

    private fun setupListViewSimple() {
        val data = listOf(
            mapOf(
                KEY_TITLE to "first title 111",
                KEY_DESCRIPTION to "some description for first title"
            ),
            mapOf(
                KEY_TITLE to "second title 222",
                KEY_DESCRIPTION to "some description for second title"
            ),
            mapOf(
                KEY_TITLE to "third title 333",
                KEY_DESCRIPTION to "some description for third title"
            )
        )
        val adapter = SimpleAdapter(
            this,
            data,
            R.layout.list_item,
            arrayOf(KEY_TITLE, KEY_DESCRIPTION),
            intArrayOf(R.id.tv_1, R.id.tv_2)
        )
        binding.listView.adapter = adapter
        binding.listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemTitle = data[position][KEY_TITLE]
                val selectedItemDescription = data[position][KEY_DESCRIPTION]
                val dialog = AlertDialog.Builder(this)
                    .setTitle(selectedItemTitle)
                    .setMessage(selectedItemDescription)
                    .setPositiveButton("Ok") { dialog, which ->
                        Toast.makeText(this, "Close with OK button", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancel") { _, _ ->
                        Toast.makeText(this, "Close with Cancel button", Toast.LENGTH_SHORT).show()
                    }
                    .create()
                dialog.show()
            }

    }


    companion object {
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
    }
}