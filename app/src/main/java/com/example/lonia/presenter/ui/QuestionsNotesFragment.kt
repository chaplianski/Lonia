package com.example.lonia.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.lonia.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class QuestionsNotesFragment : Fragment() {

    private val fragList = listOf(
        QuestionsFragment.getInstance(),
        NotesFragment.getInstance()
    )

    private val titleList = listOf(
        "Questions",
        "Notes"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activitySupport = activity as AppCompatActivity
        activitySupport.title = "Briefcases"
        activitySupport.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activitySupport.supportActionBar?.setDisplayShowHomeEnabled(true)
        return inflater.inflate(R.layout.fragment_questions_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val tableLayout: TabLayout = view.findViewById(R.id.tl_questions_notes_fragment)
        val pager: ViewPager2 = view.findViewById(R.id.vp_questions_notes_fragment)
        val beginPosition = arguments?.getInt("note", 0)

        pager.adapter = QuestionsNotesAdapter(this, fragList)

        if (beginPosition == 1){
            pager.setCurrentItem(1, false)
        }

        TabLayoutMediator(tableLayout, pager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MyLog", "Click back")
        if (item.itemId == android.R.id.home){
            val navController = view?.let { Navigation.findNavController(it) }
            navController?.navigate(R.id.action_questionsNotesFragment_to_briefCaseFragment)
        }
        return true
    }

    class QuestionsNotesAdapter(fragment: Fragment, private val list: List<Fragment>) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return list.size
        }

        override fun createFragment(position: Int): Fragment {
            return list[position]
        }
    }
}