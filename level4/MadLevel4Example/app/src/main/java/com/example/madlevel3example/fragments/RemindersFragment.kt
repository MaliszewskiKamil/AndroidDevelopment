package com.example.madlevel3example.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3example.R
import com.example.madlevel3example.adapters.ReminderAdapter
import com.example.madlevel3example.data.Reminder
import com.example.madlevel3example.database.ReminderRepository
import kotlinx.android.synthetic.main.fragment_reminders.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RemindersFragment : Fragment() {

    private lateinit var reminderRepository: ReminderRepository

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeAddReminderResults()

        reminderRepository = ReminderRepository(requireContext())
        getRemindersFromDatabase()
    }

    private fun getRemindersFromDatabase() {
        val reminders = reminderRepository.getAllReminders()
        this@RemindersFragment.reminders.clear()
        this@RemindersFragment.reminders.addAll(reminders)
        reminderAdapter.notifyDataSetChanged()
    }

    private fun initView() {
        rvReminders.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvReminders.adapter = reminderAdapter
        rvReminders.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvReminders)
    }

    private fun observeAddReminderResults(){
        setFragmentResultListener(REQ_REMINDER_KEY) {
            key, bundle -> bundle.getString(BUNDLE_REMINDER_KEY)?.let {
            val reminder = Reminder(it)

            reminderRepository.insertReminder(reminder)
            getRemindersFromDatabase()
        } ?: Log.e("ReminderFragment", "Request triggered, but empty reminder text!!")
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }

        }
        return ItemTouchHelper(callback)
    }

}